package org.example.productcatalogservice_april.services;

import jakarta.annotation.Nullable;
import org.example.productcatalogservice_april.dtos.FakeStoreProductDto;
import org.example.productcatalogservice_april.dtos.ProductDto;
import org.example.productcatalogservice_april.models.Category;
import org.example.productcatalogservice_april.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    public ProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
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
            throw new IllegalArgumentException("Invalid ProductId, Please try out with some other productId");
        }
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class,productId).getBody();
        return getProduct(fakeStoreProductDto);
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = restTemplate.postForEntity("https://fakestoreapi.com/products",productDto,FakeStoreProductDto.class).getBody();
        return getProduct(fakeStoreProductDto);
    }

    @Override
    public Product replaceProduct(Long productId, ProductDto productDto) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = putForEntity(HttpMethod.PUT,"https://fakestoreapi.com/products/{id}", productDto,FakeStoreProductDto.class,productId);
        return getProduct(fakeStoreProductDtoResponseEntity.getBody());
    }

    private <T> ResponseEntity<T> putForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables)  throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
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
}
