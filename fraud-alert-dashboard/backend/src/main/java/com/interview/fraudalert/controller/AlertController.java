package com.interview.fraudalert.controller;
import com.interview.fraudalert.service.AlertBroadcaster; import org.springframework.http.MediaType; import org.springframework.web.bind.annotation.*; import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
@RestController @RequestMapping("/api/alerts")
public class AlertController { private final AlertBroadcaster broadcaster; public AlertController(AlertBroadcaster broadcaster){this.broadcaster=broadcaster;} @GetMapping(value="/stream", produces=MediaType.TEXT_EVENT_STREAM_VALUE) public SseEmitter stream(){ return broadcaster.subscribe(); } }
