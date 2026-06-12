package com.interview.fraudalert.service;
import org.junit.jupiter.api.Test; import static org.assertj.core.api.Assertions.assertThat;
class AlertBroadcasterTest { @Test void tracksConnections(){ AlertBroadcaster b=new AlertBroadcaster(); var e=b.subscribe(); assertThat(b.activeConnections()).isEqualTo(1); e.complete(); } }
