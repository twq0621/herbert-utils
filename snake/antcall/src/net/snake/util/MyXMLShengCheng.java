package net.snake.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import net.snake.dao.map.Map;
import net.snake.dao.transport2.Transport2;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.XMLOutputter;



public class MyXMLShengCheng {
	private static final Logger logger = Logger.getLogger(MyXMLShengCheng.class);
	public void BuildXMLDoc(List list) throws IOException, JDOMException {

		Element root = new Element("data");
		Document Doc = new Document(root);

		root.addContent(new Element("rollMode").setText("0"));
		root.addContent(new Element("rollSpeed").setText("5000"));
		//root.addContent(new Element("size").setText("图片锟斤拷小 494 * 272"));
		for (int i = 0; i<list.size() ; i++) {
//			TbBillboard tbBillboard = (TbBillboard) list.get(i);
//			Element elements = new Element("pic");
//			elements.setAttribute("title", "" + tbBillboard.getTitle());
//			elements.setAttribute("target", "" + tbBillboard.getTarget());
//			elements.addContent(new Element("url").setText(tbBillboard.getUrl()));
//			elements.addContent(new Element("pageUrl").setText(tbBillboard.getPageurl()));
//			root.addContent(elements);
		}
//		XMLOutputter XMLOut = new XMLOutputter();
//		XMLOut.output(Doc, new FileOutputStream(PropUtils.getProperties("project.adPath")));
	}
	
	
	public static void BuildXMLDoc2(List<Map> list) throws IOException, JDOMException {

		Element root = new Element("root");
		Document Doc = new Document(root);
		Map map =new Map();
		map.setMapId(1);
		map.setFileName("haha");
		Element element=new Element("scene");
		
		element.setAttribute("id",map.getMapId().toString());
		element.setAttribute("name",map.getFileName());
		Element element2=new Element("transOut");
		Element element3=new Element("transOut");
        /** 鍔犲叆transOut鑺傜偣 */
        Element ownerElement = element2;
        Element ownerElement2 = element3;
        Transport2 transport2 = new Transport2();
        ownerElement.setAttribute("id","d");
        ownerElement.setAttribute("x","d");
        ownerElement.setAttribute("y","d");
        ownerElement.setAttribute("targetSceneID","d");
        
   
        ownerElement2.setAttribute("id","d");
        ownerElement2.setAttribute("x","d");
        ownerElement2.setAttribute("y","d");
        ownerElement2.setAttribute("targetSceneID","d");
        element.addContent(ownerElement);
        element.addContent(ownerElement2);
        
        
      
       
     

		root.addContent(element);
		
		
		
		//root.addContent(new Element("size").setText("图片锟斤拷小 494 * 272"));
//		for (int i = 0; i<list.size() ; i++) {
//			TbBillboard tbBillboard = (TbBillboard) list.get(i);
//			Element elements = new Element("pic");
//			elements.setAttribute("title", "" + tbBillboard.getTitle());
//			elements.setAttribute("target", "" + tbBillboard.getTarget());
//			elements.addContent(new Element("url").setText(tbBillboard.getUrl()));
//			elements.addContent(new Element("pageUrl").setText(tbBillboard.getPageurl()));
//			root.addContent(elements);
//		}
		System.out.println(Doc.toString());
		XMLOutputter XMLOut = new XMLOutputter();
		XMLOut.output(Doc, new FileOutputStream("c:/1.xml"));
	}
	
	public static void main(String[] aa){
		List list =null;
		try {
			BuildXMLDoc2(list);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		} catch (JDOMException e) {
			logger.error(e.getMessage(),e);
		}
	}
}
