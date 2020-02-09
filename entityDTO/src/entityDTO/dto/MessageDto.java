package entityDTO.dto;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Class qui encapsule un objet qui doit être transmis
 * sur le réseau
 * Lorsqu'une App souhaite communiquer avec une autre app, elle utilise obligatoirement cette classe
 * Les objets transmis sont cryptés à l'envoi et décrypter à l'arrivée pour la lecture 
 * * @author mehdi
 *
 */
public class MessageDto{
	
	private String from;
	private String to;
	private String messageTxt;
	private Object object;
	
	public MessageDto() {
		super();
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMessageTxt() {
		return messageTxt;
	}
	public void setMessageTxt(String messageTxt) {
		this.messageTxt = messageTxt;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object o) {
		this.object = o;
	}
	
	// pour tous les attributs de l'objet embarqué de type String, les encrypter
	
	public void crypt(Object o) {
		Class<?> metadata = o.getClass();
		Field[] e = metadata.getDeclaredFields();
		List<Field> l = Arrays.asList(e);
		for(Field field: l) {
			Class<?> fieldClassType = field.getType();
			if(fieldClassType.isInstance(String.class)) {
				System.out.println("je suis un attribut de type String et on va me crypter");
			}else if (fieldClassType.isInstance(List.class)) {
				System.out.println("je suis un attribut de type List et on va me parcourir ");

			};
		}
	}
	
}


