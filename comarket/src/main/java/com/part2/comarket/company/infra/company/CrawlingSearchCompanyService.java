package com.part2.comarket.company.infra.company;

import com.part2.comarket.common.exception.CustomException;
import com.part2.comarket.common.exception.ErrorCode;
import com.part2.comarket.company.command.domain.Company;
import com.part2.comarket.company.query.application.SearchCompanyService;
import com.part2.comarket.company.query.dto.response.SearchCompanyResponseDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlingSearchCompanyService implements SearchCompanyService {

    private static final String BASE_URL = "https://bizno.net/?query=";
    @Override
    public List<SearchCompanyResponseDTO> searchCompany(String keyword) {
        String url = BASE_URL + keyword;
        List<Company> companies = new ArrayList<>();
        List<SearchCompanyResponseDTO> companyResponseDTOS = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements elementsWithClass = document.getElementsByClass("single-post");

            for (Element element : elementsWithClass) {
                // 회사명 추출
                String companyName = getCompanyName(element);

                // 대표자명 추출
                String ceoName = getCeoName(element);

                // 도로명주소 추출
                String address = getAddress(element);

                // 사업자등록번호 추출
                String registrationNumber = getRegistrationNumber(element);

                // 회사 정보 추가
                // 추후에 비동기로 DB에 저장하는 로직 추가
                Company company = new Company(companyName, registrationNumber, address, ceoName);
                companies.add(company);

                companyResponseDTOS.add(SearchCompanyResponseDTO.fromEntity(company));
            }
        } catch (SSLHandshakeException e) {
            throw new CustomException(ErrorCode.COMPANY_CRAWLING_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return companyResponseDTOS;
        }
    }

    private String getCompanyName(Element element) {
        Element companyElement = element.select("a[href]").first();
        return companyElement != null ? companyElement.text() : "";
    }

    private String getCeoName(Element element) {
        Element ceoElement = element.select("h5:contains(대표자명)").first();
        String ceoName = "";
        if (ceoElement != null) {
            String[] ceoParts = ceoElement.text().split(":");
            if (ceoParts.length >= 2) {
                ceoName = ceoParts[1].trim();
            }
        }
        return ceoName;
    }

    private String getAddress(Element element) {
        Element addressElement = element.select("p").first();
        String address = "";
        if (addressElement != null) {
            String[] addressParts = addressElement.text().split("도로명주소 : ");
            if (addressParts.length >= 2) {
                // 특정 패턴을 지우고 도로명주소를 추출
                address = addressParts[1].replaceAll("\\.{3}|\\(\\.\\.\\.", "").trim();
            }
        }
        return address;
    }

    private String getRegistrationNumber(Element element) {
        Element companyElement = element.select("a[href]").first();
        String href = companyElement != null ? companyElement.attr("href") : "";
        String registrationNumber = "";
        if (href != null) {
            String[] parts = href.split("/");
            if (parts.length >= 3) {
                registrationNumber = parts[2];
            }
        }
        return registrationNumber;
    }
}
