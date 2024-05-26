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

    //Case - 1
    //public List<Product> searchProducts(String query) {

    //Case - 2
    //public List<Product> searchProducts(String query, int pageSize, int pageNumber) {

    //Case - 3
    //public Page<Product> searchProducts(String query, int pageSize, int pageNumber) {

    public Page<Product> searchProducts(String query, int pageSize, int pageNumber, List<SortParam> sortParams) {
        //Case 1 (returning list with all results, no paging)
        //return productRepo.findByNameEquals(query);

        //Case 2 (returning list with paging capability) and 3 (returning page)
        //return productRepo.findByNameEquals(query, PageRequest.of(pageNumber,pageSize));

        //Case 4 (returning page with sort applied on price{hardcoded})
        //Sort sort = Sort.by("price").descending();
        //return productRepo.findByNameEquals(query, PageRequest.of(pageNumber,pageSize,sort));

        //Case 5
        Sort sort = null;

        if(!sortParams.isEmpty()) {
            if(sortParams.get(0).getSortType().equals(SortType.ASC))
                 sort = Sort.by(sortParams.get(0).getParamName());
            else
                 sort = Sort.by(sortParams.get(0).getParamName()).descending();
        }

        for(int i=1;i<sortParams.size();i++) {
            if(sortParams.get(0).getSortType().equals(SortType.ASC))
                sort = sort.and(Sort.by(sortParams.get(i).getParamName()));
            else
                sort = sort.and(Sort.by(sortParams.get(i).getParamName()).descending());
        }

        return productRepo.findByNameEquals(query, PageRequest.of(pageNumber,pageSize,sort));
    }
}