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

    private int pageNumber;

    private int pageSize;

    private List<SortParam> sortParamList = new ArrayList<>();
}
