package com.foretruff.jdbc.starter;

import com.foretruff.jdbc.starter.dao.TicketDao;
import com.foretruff.jdbc.starter.entity.TicketEntity;

import java.math.BigDecimal;

// Alt + M
// CTRL + SHIFT + N
public class DaoRunner {
    public static void main(String[] args) {
        var ticketDao = TicketDao.getInstance();
        var delete = ticketDao.delete(56L);
        System.out.println(delete);
//        saveTicket();
    }

    private static void saveTicket() {
        var ticketDao = TicketDao.getInstance();
        var ticketEntity = new TicketEntity();
        ticketEntity.setPassengerNo("ABC");
        ticketEntity.setPassengerName("Maksim Rokitko");
        ticketEntity.setFlightId(1L);
        ticketEntity.setSeatNo("B3");
        ticketEntity.setCost(BigDecimal.TEN);
        var savedTicket = ticketDao.save(ticketEntity);
        System.out.println(savedTicket);
    }
}
