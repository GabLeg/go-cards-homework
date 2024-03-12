package com.example.gocardshomework.api.controllers.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrencyInterceptor implements HandlerInterceptor {

  private static final String GAME_ID_ATTRIBUTE = "gameIdAttribute";
  private static final Map<String, Lock> LOCK_MAP = new HashMap<>();

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String gameId = extractGameId(request.getRequestURI());
    request.setAttribute(GAME_ID_ATTRIBUTE, gameId);

    acquireLock(gameId);
    return HandlerInterceptor.super.preHandle(request, response, handler);
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) throws Exception {
    String gameId = (String) request.getAttribute(GAME_ID_ATTRIBUTE);

    Lock lock = LOCK_MAP.get(gameId);
    lock.unlock();
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }

  private String extractGameId(String requestUrl) {
    List<String> pathParams = List.of(requestUrl.split("/"));
    return pathParams.get(pathParams.indexOf("games") + 1);
  }

  private synchronized void acquireLock(String gameId) {
    if (LOCK_MAP.containsKey(gameId)) {
      Lock lock = LOCK_MAP.get(gameId);
      lock.lock();
    } else {
      Lock reentrantLock = new ReentrantLock();
      reentrantLock.lock();
      LOCK_MAP.put(gameId, reentrantLock);
    }
  }
}
