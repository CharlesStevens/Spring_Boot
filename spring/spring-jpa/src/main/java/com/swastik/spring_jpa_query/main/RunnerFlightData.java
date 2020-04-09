package com.swastik.spring_jpa_query.main;

import com.swastik.spring_jpa_query.config.JpaPersistance;
import com.swastik.spring_jpa_query.model.FlightData;
import com.swastik.spring_jpa_query.service.FlightDataService;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunnerFlightData {

  public static void main(String[] args) throws Exception {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.register(JpaPersistance.class);
    ctx.refresh();

    FlightDataService service = (FlightDataService) ctx.getBean("flightDataService");
//    List<FlightData> flData = getFlightDataCollection();
//    service.saveAllFlightData(flData);
//    int i = 0;
//    for (FlightData allFlightDatum : service.getAllFlightData()) {
//      i++;
//      if (i > 100) {
//        break;
//      }
//      System.out.println(allFlightDatum.toString());
//    }

//    Pageable p = PageRequest.of(0, 3, Sort.by("airport"));
//    Page<FlightData> page = service.getAllPageFlightData(p);
//    System.out.println(page.getTotalPages());
//    System.out.println(page.getTotalElements());

    List<FlightData> flightDataByAirport = service.getNamedQueryResult("BIS");
    System.out.println(flightDataByAirport.size());

  }

  private static List<FlightData> getFlightDataCollection() throws Exception {
    List<String> lineContent = FileUtils.readLines(
        new File("C:\\Users\\Abhishek\\hdfs\\data\\FlightRecordData\\airline_delay_causes.csv"));
    List<FlightData> flightDataCollection = new ArrayList<>();

    for (int i = 1; i < lineContent.size(); i++) {
      String cnt = lineContent.get(i);
      StringBuilder builder = new StringBuilder();

      boolean started = false;
      for (int j = 0; j < cnt.toCharArray().length; j++) {
        if (cnt.charAt(j) == '"') {
          if (!started) {
            started = true;
            continue;
          } else {
            started = false;
            continue;
          }
        }

        if (started && cnt.charAt(j) == ',') {
          builder.append(" ");
        } else {
          builder.append(cnt.charAt(j));
        }

      }

      String[] content = builder.toString().split(",");
      System.out.println(builder.toString());
//      DelimitedLineTokenizer s1 = new DelimitedLineTokenizer(',');
//      s1.setQuoteCharacter('"');
//      FieldSet t = s1.tokenize(lineContent.get(i));
//      String[] content = t.getValues();
      FlightData fl = null;
      try {
        fl = new FlightData(
            content[0] == null ? null : Double.valueOf(content[0]).intValue(),
            content[1] == null ? null : Double.valueOf(content[1]).intValue(),
            content[2], content[3], content[4], content[5],
            content[6] == null ? null : Double.valueOf(content[6]).intValue(),
            content[7] == null ? null : Double.valueOf(content[7]).intValue(),
            content[8] == null ? null : Double.valueOf(content[8]),
            content[9] == null ? null : Double.valueOf(content[9]),
            content[10] == null ? null : Double.valueOf(content[10]),
            content[11] == null ? null : Double.valueOf(content[11]),
            content[12] == null ? null : Double.valueOf(content[12]),
            content[13] == null ? null : Double.valueOf(content[13]).intValue(),
            content[14] == null ? null : Double.valueOf(content[14]).intValue(),
            content[15] == null ? null : Double.valueOf(content[15]).intValue(),
            content[16] == null ? null : Double.valueOf(content[16]).intValue(),
            content[17] == null ? null : Double.valueOf(content[17]).intValue(),
            content[18] == null ? null : Double.valueOf(content[18]).intValue(),
            content[19] == null ? null : Double.valueOf(content[19]).intValue(),
            content[20] == null ? null : Double.valueOf(content[20]).intValue());
      } catch (Exception e) {
        e.printStackTrace();
        continue;
      }

      flightDataCollection
          .add(fl);
    }

    return flightDataCollection;
  }

}
