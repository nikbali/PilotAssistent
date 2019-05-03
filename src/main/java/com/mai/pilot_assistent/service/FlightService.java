package com.mai.pilot_assistent.service;

import com.mai.pilot_assistent.controller.dto.CreateFlightRequest;
import com.mai.pilot_assistent.model.Flight;
import com.mai.pilot_assistent.model.User;
import com.mai.pilot_assistent.security.CurrentUser;
import com.mai.pilot_assistent.security.UserPrincipal;
import com.mai.pilot_assistent.util.Result;

public interface FlightService {


     /**
      * Метод создает новый полет в статусе Запланирован для текущего пользователя
      * @param request dto c информацией о полете
      * @param userPrincipal текущий пользователь
      * @return созданый optional объект {@link Flight}
      */
     Result<Flight> createFlight(@CurrentUser UserPrincipal userPrincipal, CreateFlightRequest request);

     Result<Flight> createFlight(String username, CreateFlightRequest request);

     Result<Flight> createFlight(User user, CreateFlightRequest request);
}
