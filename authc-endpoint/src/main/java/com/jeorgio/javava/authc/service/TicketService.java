package com.jeorgio.javava.authc.service;

import com.jeorgio.javava.authc.vo.QrcodeLoginVo;

public interface TicketService {

    QrcodeLoginVo createTicket();

    void updateTicket(String ticket, String subject);

    String getTicket(String ticket);
}
