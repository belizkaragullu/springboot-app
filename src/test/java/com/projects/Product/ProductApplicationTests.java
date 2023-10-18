package com.projects.Product;

import com.projects.Product.dbmodel.Product;
import com.projects.Product.exception.RunTimeBusinessException;
import com.projects.Product.repository.ProductRepository;
import com.projects.Product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductApplicationTests {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	private Product firstProduct = new Product();
	private Product secondProduct= new Product();

	private static final long ID_FIRST = 5;
	private static final long ID_SECOND =6;
	private static final String NAME_FIRST = "TEST PRODUCT 1";
	private static final String NAME_SECOND = "TEST PRODUCT 2";
	private static final Integer PRICE_FIRST =20;
	private static final Integer PRICE_SECOND=50;
	private static final Integer QUANTITY_FIRST=15;
	private static final Integer QUANTITY_SECOND =30;


	@BeforeEach
	public void init(){
		firstProduct.setId(ID_FIRST);
		firstProduct.setName(NAME_FIRST);
		firstProduct.setPrice(PRICE_FIRST);
		firstProduct.setQuantity(QUANTITY_FIRST);

		secondProduct.setId(ID_SECOND);
		secondProduct.setName(NAME_SECOND);
		secondProduct.setPrice(PRICE_SECOND);
		secondProduct.setQuantity(QUANTITY_SECOND);
	}

	@DisplayName("JUnit test for Save method")
	@Test
	public void givenProductToSave_ShouldReturnProduct(){
		when(productRepository.save(any())).thenReturn(firstProduct);
		Product returnProduct = productService.saveProduct(firstProduct);

		assertEquals(5,returnProduct.getId());
	}

	@DisplayName("JUnit test for GetAll method")
	@Test
	public void givenGetAllProductShouldReturnListOfAllProducts(){
		Product product1 = new Product(1,"get1",20,20);
		Product product2 = new Product(2,"get2",30,30);
		Product product3 = new Product(3,"get3",40,40);

		when(productRepository.findAll()).thenReturn(List.of(product1,product2,product3));

		List<Product> productList = productService.getAllProducts();
		assertEquals("get2",productList.get(1).getName());
	}

	@DisplayName("JUnit test for Delete method")
	@Test
	public void givenProductId_whenDelete_thenNothing() throws Exception{

		productService.deleteProduct(firstProduct.getId());
		verify(productRepository, times(1)).delete(any());
	}

	@DisplayName("JUnit test for Update method")
	@Test
	public void givenProductToUpdate_ShouldReturnUpdatedProduct(){

		Product updatedProduct= new Product(1,"Beliz",20,20);

		when(productRepository.save(firstProduct)).thenReturn(updatedProduct);
		Product responseProduct = productService.updateProduct(firstProduct);

		assertNotEquals(responseProduct.getName(), firstProduct.getName());
	}

	@DisplayName("JUnit test for correct calculation accuracy to increase price method ")
	@Test
	public void givenValidPercentageToIncreasePrice_ShouldCalculateCorrect(){

		double percentage =10;

		Product product1 = new Product(1,"FirstIncrease",20,10);
		Product product2 = new Product(2,"SecondIncrease",30,20);

		when(productRepository.findAll()).thenReturn(List.of(product1,product2));
		List<Product> response =productService.increaseAllProductsPriceByPercentage(percentage);

		assertNotNull(response);
		assertTrue(product1.getPrice()==11);
		assertEquals(22, (long)product2.getPrice());
	}

	@DisplayName("JUnit test for null list to increase price method")
	@Test
	public void increasePriceServiceShouldReturnEmptyList_whenProductListHasNoRecord(){

		double percentage =10;

		when(productRepository.findAll()).thenReturn(Collections.emptyList());
		List<Product> response = productService.increaseAllProductsPriceByPercentage(percentage);

		assertTrue(response.isEmpty());
	}


	@DisplayName("JUnit test for high percentage value exception to increase price method")
	@Test
	public void givenFalsePercentage_ShouldReturnException(){

		double percentage=51;


		assertThrows(RunTimeBusinessException.class,()-> productService.increaseAllProductsPriceByPercentage(percentage));
	}

	@DisplayName("JUnit test for negative percentage value exception to increase price method")
	@Test
	public void givenNegativePercentage_ShouldReturnException(){
		double percentage = -1;

		assertThrows(RunTimeBusinessException.class,()-> productService.increaseAllProductsPriceByPercentage(percentage));
	}
}