package or.clearn.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
 
public class MySocketHandler extends TextWebSocketHandler{
    private static Logger logger = LoggerFactory.getLogger(MySocketHandler.class);
    
    //����� �ϴ���ê�� map���
//    Map<String, WebSocketSession> sessions  = new HasMap<String, WebSocketSession>();*/
    
    private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
    /**
     * Ŭ���̾�Ʈ ���� ���Ŀ� ����Ǵ� �޼ҵ�
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        //Map����
//        session.put(session.getId(), session);
        
        //List����
        sessionList.add(session);
        logger.info("{} �����", session.getId());
        System.out.println(session.getId() + "���� �����߽��ϴ�.");
        System.out.println("���� IP : " + session.getRemoteAddress().getHostName());
        System.out.println("�����ڼ�1  :"+sessionList.size());
        System.out.println("-----------------");
    }
    /**
     * Ŭ���̾�Ʈ�� �����ϼ����� �޽����� �������� �� ����Ǵ� �޼ҵ�
     */
    @Override
    protected void handleTextMessage(WebSocketSession session,
         
        TextMessage message) throws Exception {

        logger.info("{}�� ���� {} ����", session.getId(), message.getPayload());
        
        //�迭�̸� ���� ���� �ְ�, ���������� �ִ� 2���ӿ�
        /*logger.info("{}�� ���� {}����",new String[] {session.getId(), message.getPayload()});*/
        
        //����Ǿ��ִ� ��� Ŭ���̾�Ʈ�鿡�� �޽����� �����Ѵ� 
        //session.sendMessage(new TextMessage("echo:" + message.getPayload()));

        System.out.println("receive message1:"+message.toString());
        System.out.println("receive message2:"+message.getPayload());
 

        for(WebSocketSession sess : sessionList){
            sess.sendMessage(new TextMessage("echo: " +  message.getPayload()));
        }
        System.out.println("�����ڼ�2  :"+sessionList.size());
        String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println(uuid);
        /*map���
        Interator<String> sessionIds = sessions.keySet().iterator();
        String sessionId="";
        while(sessionIds.hasNext()){s
            sessionId = sessionIds.next();
            sessions.get(sessionId).sendMessaget(new TextMessage("echo:" + message.getPayload()));
            
        }*/
        
    }
    /**
     * Ŭ���̾�Ʈ�� ������ ������ �� ����Ǵ� �޼ҵ�
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session,
            CloseStatus status) throws Exception {
        //list
        sessionList.remove(session);
        //map
        //sessions.remove(session.getId());
        //logger.info("{} ���� ����", session.getId());
        for (WebSocketSession webSocketSession : sessionList) {
            /*
             * �ڽ��� ���� �޽����� ���� �ʴ´�.
             */
            if (!session.getId().equals(webSocketSession.getId())) {
                webSocketSession.sendMessage(new TextMessage(session.getRemoteAddress().getHostName() + "�����߽��ϴ�."));
            }
        }
        System.out.println("�����ڼ�3  :"+sessionList.size());
        
    }
    
    
}
 
