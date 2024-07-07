package listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static final ConcurrentHashMap<String, CopyOnWriteArrayList<String>> userSessions = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, HttpSession> sessionMap = new ConcurrentHashMap<>();

    public SessionListener() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
//        System.out.println("Session created: " + se.getSession().getId());
        sessionMap.put(se.getSession().getId(), se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
        String sessionId = se.getSession().getId();
        sessionMap.remove(sessionId);
        userSessions.forEach((userId, sessions) -> {
            sessions.remove(sessionId);
            if (sessions.isEmpty()) {
                userSessions.remove(userId);
            }
        });
    }

    public static void addSession(String userId, HttpSession session) {
        userSessions.computeIfAbsent(userId, k -> new CopyOnWriteArrayList<>()).add(session.getId());
    }

    public static CopyOnWriteArrayList<String> getSessions(String userId) {
        return userSessions.getOrDefault(userId, new CopyOnWriteArrayList<>());
    }

    public static HttpSession getSessionById(String sessionId) {
        return sessionMap.get(sessionId);
    }
}