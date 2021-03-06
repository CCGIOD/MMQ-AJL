package managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Permet de charger les ressources via le fichier xml. Fichier non document�.
 */
public class RessourcesManager {
	
	private static RessourcesManager _instance = new RessourcesManager();

	//private Map soundMap;
	private Map<String, Image> imageMap;

	public Image getImage(String str){
		return (Image) imageMap.get(str);
	}
	
	public boolean testImage(String str){
		return imageMap.containsKey(str);
	}

	public RessourcesManager(){
		//soundMap = new HashMap();
		imageMap = new HashMap<String, Image>();
	}

	public final static RessourcesManager getInstance(){
		try {
			_instance.loadResources(new FileInputStream(new File("ressources/ressources.xml")));
		} catch (FileNotFoundException | SlickException e) {
			e.printStackTrace();
		}
		return _instance;
	}

	public void loadResources(InputStream is) throws SlickException{
		loadResources(is, false);
	}

	public void loadResources(InputStream is, boolean deferred) throws SlickException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new SlickException("Could not load resources", e);
		}
		
		Document doc = null;
		try {
			doc = docBuilder.parse (is);
		} catch (Exception e) {throw new SlickException("Could not load resources", e);		}

		doc.getDocumentElement ().normalize ();

		NodeList listResources = doc.getElementsByTagName("ressource");

		int totalResources = listResources.getLength();

		if(deferred)
			LoadingList.setDeferredLoading(true);

		for(int resourceIdx = 0; resourceIdx < totalResources; resourceIdx++){

			Node resourceNode = listResources.item(resourceIdx);

			if(resourceNode.getNodeType() == Node.ELEMENT_NODE){
				Element resourceElement = (Element)resourceNode;

				String type = resourceElement.getAttribute("type");//on r�cup�re le type !

				if(type.equals("image")){
					addElementAsImage(resourceElement);
				}else if(type.equals("sound")){
					//addElementAsSound(resourceElement);
				}else if(type.equals("text")){
					//addElementAsText(resourceElement);
				}else if(type.equals("font")){

				}else if(type.equals("animation")){
					//addElementAsAnimation(resourceElement);
				}
			}
		}

	}

	private final void addElementAsImage(Element resourceElement) throws SlickException {
		loadImage(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}

	public Image loadImage(String id, String path) throws SlickException{
		if(path == null || path.length() == 0)
			throw new SlickException("Image resource [" + id + "] has invalid path");

		Image image = null;
		try{
			image = new Image(path);
		} catch (SlickException e) {
			throw new SlickException("Could not load image", e);
		}

		this.imageMap.put(id, image);

		return image;
	}
}
