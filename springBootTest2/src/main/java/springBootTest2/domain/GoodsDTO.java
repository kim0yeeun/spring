package springBootTest2.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class GoodsDTO {
	String goodsNum;
	String goodsName;
	Integer goodsPrice;
	Date goodsDate;
	String goodsContent;
	Integer goodsQty;
	String goodsCompany;
	Integer empNum;
	String goodsImages;
}
