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
    
    //방법일 일대일챗팅 map사용
//    Map<String, WebSocketSession> sessions  = new HasMap<String, WebSocketSession>();*/
    
    private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
    /**
     * 클라이언트 연결 이후에 실행되는 메소드
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        //Map사용시
//        session.put(session.getId(), session);
        
        //List쓸때
        sessionList.add(session);
        logger.info("{} 연결됨", session.getId());
        System.out.println(session.getId() + "님이 접속했습니다.");
        System.out.println("연결 IP : " + session.getRemoteAddress().getHostName());
        System.out.println("접속자수1  :"+sessionList.size());
        System.out.println("-----------------");
    }
    /**
     * 클라이언트가 웹소켓서버로 메시지를 전송했을 때 실행되는 메소드
     */
    @Override
    protected void handleTextMessage(WebSocketSession session,
         
        TextMessage message) throws Exception {

        logger.info("{}로 부터 {} 받음", session.getId(), message.getPayload());
        
        //배열이면 많이 쓸수 있고, 쓰지않으면 최대 2개임여
        /*logger.info("{}와 부터 {}받음",new String[] {session.getId(), message.getPayload()});*/
        
        //연결되어있는 모든 클라이언트들에게 메시지를 전송한다 
        //session.sendMessage(new TextMessage("echo:" + message.getPayload()));

        System.out.println("receive message1:"+message.toString());
        System.out.println("receive message2:"+message.getPayload());
 

        for(WebSocketSession sess : sessionList){
            sess.sendMessage(new TextMessage("echo: " +  message.getPayload()));
        }
        System.out.println("접속자수2  :"+sessionList.size());
        String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println(uuid);
        /*map사용
        Interator<String> sessionIds = sessions.keySet().iterator();
        String sessionId="";
        while(sessionIds.hasNext()){s
            sessionId = sessionIds.next();
            sessions.get(sessionId).sendMessaget(new TextMessage("echo:" + message.getPayload()));
            
        }*/
        
    }
    /**
     * 클라이언트가 연결을 끊었을 때 실행되는 메소드
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session,
            CloseStatus status) throws Exception {
        //list
        sessionList.remove(session);
        //map
        //sessions.remove(session.getId());
        //logger.info("{} 연결 끊김", session.getId());
        for (WebSocketSession webSocketSession : sessionList) {
            /*
             * 자신이 보낸 메시지를 받지 않는다.
             */
            if (!session.getId().equals(webSocketSession.getId())) {
                webSocketSession.sendMessage(new TextMessage(session.getRemoteAddress().getHostName() + "퇴장했습니다."));
            }
        }
        System.out.println("접속자수3  :"+sessionList.size());
        
    }
    
    
}
 
