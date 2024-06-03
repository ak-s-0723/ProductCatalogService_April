package org.example.productcatalogservice_april.services;

import org.example.productcatalogservice_april.clients.FakeStore.FakeStoreApiClient;
import org.example.productcatalogservice_april.dtos.FakeStoreProductDto;
import org.example.productcatalogservice_april.dtos.ProductDto;
import org.example.productcatalogservice_april.models.Category;
import org.example.productcatalogservice_april.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private RestTemplateBuilder restTemplateBuilder;

    private FakeStoreApiClient fakeStoreApiClient;

    public ProductService(RestTemplateBuilder restTemplateBuilder, FakeStoreApiClient fakeStoreApiClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreApiClient = fakeStoreApiClient;
    }


    @Override
    public Product getProductDetails(Long productId, Long userId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //Compiler is unable to determine datatype of list which is generics, that's why we can't use List<FakeStoreProductDto>.class , instead using array
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForEntity("https://fakestoreapi.com/products/", FakeStoreProductDto[].class).getBody();
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(getProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product getProduct(Long productId) {
        if(productId == 0) {
            throw new IllegalArgumentException("Invalid productId, please pass some valid id");
        }

        FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.getProduct(productId);
        return getProduct(fakeStoreProductDto);
    }

    @Override
    public Product createProduct(Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDtoreq = getFakeStoreProductDto(product);
        FakeStoreProductDto fakeStoreProductDto = restTemplate.postForEntity("https://fakestoreapi.com/products",fakeStoreProductDtoreq,FakeStoreProductDto.class).getBody();
        return getProduct(fakeStoreProductDto);
    }

    @Override
    public Product replaceProduct(Long id,Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDtoreq = getFakeStoreProductDto(product);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                putForEntity("https://fakestoreapi.com/products/{id}",
                        fakeStoreProductDtoreq, FakeStoreProductDto.class,id);
        return getProduct(fakeStoreProductDtoResponseEntity.getBody());
    }

    private <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }

    private Product getProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDto getFakeStoreProductDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setTitle(product.getName());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }
}
