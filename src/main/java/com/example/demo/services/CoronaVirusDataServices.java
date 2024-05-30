package com.example.demo.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.model.Reports;

@Service
public class CoronaVirusDataServices 
{

	private List<Reports> allReport=new ArrayList<Reports>();
	
	
	public List<Reports> getAllReport() {
		return allReport;
	}
	
	public void setAllReport(List<Reports> allReport) {
		this.allReport = allReport;
	}
	
	private static String virusDataUrl="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
	
	@PostConstruct
	@Scheduled(cron = "* * * 1 * *")
	public void fetchVirusData() throws IOException, InterruptedException
	{
		List<Reports> newReports=new ArrayList<Reports>();
	HttpClient client=HttpClient.newHttpClient();
	HttpRequest request=HttpRequest.newBuilder().uri(URI.create(virusDataUrl)).build();
	HttpResponse<String>httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
	
	StringReader csvBodyreader=new StringReader(httpResponse.body());
	Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyreader);
	
	for (CSVRecord record : records) 
	{
		Reports rep=new Reports();
	    rep.setState(record.get("Province/State"));
	    rep.setCountry(record.get("Country/Region"));
	    int latestCase=Integer.parseInt(record.get(record.size()-1));
	    int PrevCase=Integer.parseInt(record.get(record.size()-2));
	    rep.setTotalDeaths(latestCase);
	    rep.setDifferFromPrevDay(latestCase-PrevCase);
	   // System.out.println(rep); 
	    newReports.add(rep);    
	}
	
	this.allReport=newReports;
		
	}
}
