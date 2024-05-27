package org.example.productcatalogservice_april.services;

import org.example.productcatalogservice_april.models.Product;
import org.example.productcatalogservice_april.models.SortParam;
import org.example.productcatalogservice_april.models.SortType;
import org.example.productcatalogservice_april.repositories.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private ProductRepo productRepo;

    public SearchService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Page<Product> searchProducts(String query, int pageSize, int pageNumber, List<SortParam> sortParams) {
        //Descending order of price and if price is same, then ascending order of id
        //Sort sort = Sort.by("price").descending().and(Sort.by("id"));
        Sort sort = null;

        if(!sortParams.isEmpty()) {
            if(sortParams.get(0).getSortType().equals(SortType.ASC))
               sort = Sort.by(sortParams.get(0).getParamName());
            else
                sort = Sort.by(sortParams.get(0).getParamName()).descending();
        }

        for(int i=1; i<sortParams.size();i++) {
            if(sortParams.get(i).getSortType().equals(SortType.ASC))
                sort = sort.and(Sort.by(sortParams.get(i).getParamName()));
            else
                sort = sort.and(sort.by(sortParams.get(i).getParamName()).descending());
        }

       return productRepo.findByNameEquals(query, PageRequest.of(pageNumber,pageSize,sort));
    }
}
