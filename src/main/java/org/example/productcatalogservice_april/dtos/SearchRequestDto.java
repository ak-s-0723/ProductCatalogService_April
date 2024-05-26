package org.example.productcatalogservice_april.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservice_april.models.SortParam;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchRequestDto {
    private String query;

    private int pageSize;

    private int pageNumber;

    //Case 5
    private List<SortParam> sortParamList = new ArrayList<>();
}
