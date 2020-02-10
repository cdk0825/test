
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *df
 * @author work
 */
public class TestReadXmlFile {
	
	
	// xml 파싱 테스트
	public static void main(String[] args) throws Exception{
		
		
		Document xml = null;
		
		String file = "C:/Users/work/Desktop/업무/Project/GCO/gco_notice.xml";
		
		try{
			InputSource is = new InputSource(new InputStreamReader(new FileInputStream("C:/Users/work/Desktop/업무/Project/GCO/gco_notice.xml"),"UTF8"));
			
			xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			
			Element element = xml.getDocumentElement();
			
			NodeList list = element.getChildNodes();
			
			if(list.getLength() > 0){
				for(int i = 0; i < list.getLength(); i++){
					
					NodeList childList = list.item(i).getChildNodes();
					
					if(childList.getLength() > 0){
						for(int j = 0; j < childList.getLength(); j++){
							
							if(childList.item(j).getNodeName().equals("#text")==false) {
								System.out.println("\t xml tag name : " + childList.item(j).getNodeName() + ", xml값 : "+childList.item(j).getTextContent());                                
							}
						}
					}
					System.out.println();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
