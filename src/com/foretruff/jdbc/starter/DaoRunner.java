package com.foretruff.jdbc.starter;

import com.foretruff.jdbc.starter.dao.TicketDao;
import com.foretruff.jdbc.starter.dto.TicketFilter;
import com.foretruff.jdbc.starter.entity.TicketEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// Alt + M
// CTRL + SHIFT + N
public class DaoRunner {

    /*
    EXPLAIN ANALYSE
    SELECT *
    FROM test2
    WHERE id > 80000 //id
    LIMIT 20
    */

    public static void main(String[] args) {
        var ticketFilter = new TicketFilter(10, 0, "VOVA", null);
        var ticketDao = TicketDao.getInstance();
        ticketDao.findAll(ticketFilter).forEach(System.out::println);
    }

    private static void findAll() {
        var ticketDao = TicketDao.getInstance();
        ticketDao.findAll().forEach(System.out::println);
    }

    public static void findByIdAndUpdate() {
        var ticketDao = TicketDao.getInstance();
        var maybeTicket = ticketDao.findById(5L);
        System.out.println(maybeTicket);

        maybeTicket.ifPresent(ticket -> {
            ticket.setCost(BigDecimal.TEN);
            ticket.setPassengerName("VOVA");
            ticketDao.update(ticket);
        });
    }

    private static void delete() {
        var ticketDao = TicketDao.getInstance();
        var delete = ticketDao.delete(56L);
        System.out.println(delete);
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
