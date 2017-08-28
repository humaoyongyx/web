package issac.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("swagger")
public class SwaggerController {
	 Map<Long, Book> books = Collections.synchronizedMap(new HashMap<Long, Book>());
	 
	 
	 public  class Book{
		 
		 private Long id;
		 private String name;
		 private Double price;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		 
	 }

	    @ApiOperation(value="获取图书列表", notes="获取图书列表")
	    @RequestMapping(value={""}, method= RequestMethod.GET)
	    public List<Book> getBook() {
	        List<Book> book = new ArrayList<>(books.values());
	        return book;
	    }

	    @ApiOperation(value="创建图书", notes="创建图书")
	    @ApiImplicitParam(name = "book", value = "图书详细实体", required = true, dataType = "Book")
	    @RequestMapping(value="", method=RequestMethod.POST)
	    public String postBook(@RequestBody Book book) {
	        books.put(book.getId(), book);
	        return "success";
	    }
	    @ApiOperation(value="获图书细信息", notes="根据url的id来获取详细信息")
	    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long",paramType = "path")
	    @RequestMapping(value="/{id}", method=RequestMethod.GET)
	    public Book getBook(@PathVariable Long id) {
	        return books.get(id);
	    }

	    @ApiOperation(value="更新信息", notes="根据url的id来指定更新图书信息")
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "id", value = "图书ID", required = true, dataType = "Long",paramType = "path"),
	            @ApiImplicitParam(name = "book", value = "图书实体book", required = true, dataType = "Book")
	    })
	    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
	    public String putUser(@PathVariable Long id, @RequestBody Book book) {
	        Book book1 = books.get(id);
	        book1.setName(book.getName());
	        book1.setPrice(book.getPrice());
	        books.put(id, book1);
	        return "success";
	    }
	    @ApiOperation(value="删除图书", notes="根据url的id来指定删除图书")
	    @ApiImplicitParam(name = "id", value = "图书ID", required = true, dataType = "Long",paramType = "path")
	    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	    public String deleteUser(@PathVariable Long id) {
	        books.remove(id);
	        return "success";
	    }

	    @ApiIgnore//使用该注解忽略这个API
	    @RequestMapping(value = "/hi", method = RequestMethod.GET)
	    public String  jsonTest() {
	        return " hi you!";
	    }

}
