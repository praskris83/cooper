/**
 * 
 */
package com.hackthon.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

/**
 * @author prasad
 *
 */
@Service
public class NLPDateParser {

  ThreadLocal<Parser> threadLocalParser;

  @PostConstruct
  public void init() {
    threadLocalParser = ThreadLocal.withInitial(() -> new Parser());
  }

  public List<LocalDate> getDates(String input){
    List<LocalDate> dates = new ArrayList<LocalDate>();
    List<DateGroup> groups = threadLocalParser.get().parse(input);
    for(DateGroup group:groups) {
      List<Date> dts = group.getDates();
      System.out.println("Valid Dates --- " + dts);
      List<LocalDate> lDts= dts.stream().map(dt -> 
        dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).collect(Collectors.toList());
      dates.addAll(lDts);
    }
    return dates;
  }
}
