package org.pstcl.ea.controller;

import org.pstcl.ea.service.impl.IlossReportService;
import org.pstcl.ea.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LossReportController {
	

	@Autowired
	private IlossReportService lossReportService;
	
	@PreAuthorize("hasRole('ROLE_SLDC_USER') or hasRole('ROLE_SLDC_ADMIN')")
	@RequestMapping(value = "/getReport", method = RequestMethod.GET)
	public String getReportDetails(@RequestParam(value="type") String reportType,@RequestParam(value = "month") Integer month,
			@RequestParam(value = "year") Integer year,ModelMap modelMap) {
		modelMap.addAttribute("lossReportModel", lossReportService.getReport(reportType,month,year));
		modelMap.addAttribute("reportMonthYearDate",DateUtil.convertMonthYearToDate(month, year) );

		modelMap.addAttribute("monthOfReport", month);
		modelMap.addAttribute("yearOfReport", year);

		return "lossReport";
	}

	

	@PreAuthorize("hasRole('ROLE_SLDC_USER') or hasRole('ROLE_SLDC_ADMIN')")
	@RequestMapping(value = "/getLossReport", method = RequestMethod.GET)
	public String getLossReport(@RequestParam(value = "month") Integer month,
			@RequestParam(value = "year") Integer year,ModelMap modelMap) {
		modelMap.addAttribute("consolidatedLossReportModel", lossReportService.getConsolidatedMonthlyLossReport(month,year));
		modelMap.addAttribute("reportMonthYearDate",DateUtil.convertMonthYearToDate(month, year) );

		modelMap.addAttribute("monthOfReport", month);
		modelMap.addAttribute("yearOfReport", year);

		return "lossReportConsolidated";
	}
	
	//get Tamper Loss Report
	@PreAuthorize("hasRole('ROLE_SLDC_USER') or hasRole('ROLE_SLDC_ADMIN')")
	@RequestMapping(value = "/getTamperLossReport", method = RequestMethod.GET)
	public String getTamperLossReport(@RequestParam(value = "month") Integer month,
			@RequestParam(value = "year") Integer year,ModelMap modelMap) {
        modelMap.addAttribute("tamperDetailsProjectionModel", lossReportService.getTamperDetailsProjectionReport(month,year));
        modelMap.addAttribute("reportMonthYearDate",DateUtil.convertMonthYearToDate(month, year) );

		modelMap.addAttribute("monthOfReport", month);
		modelMap.addAttribute("yearOfReport", year);
		return "tamperDetailsProjection";
	}
	
	@PreAuthorize("hasRole('ROLE_SLDC_USER') or hasRole('ROLE_SLDC_ADMIN')")
	@RequestMapping(value = "/getLossReport", method = RequestMethod.POST)
	public String getLossReportPost(@RequestParam(value = "startDate") Integer month,
			@RequestParam(value = "endDate") Integer year,ModelMap modelMap) {
		modelMap.addAttribute("consolidatedLossReportModel", lossReportService.getConsolidatedMonthlyLossReport(month,year));
		modelMap.addAttribute("reportMonthYearDate",DateUtil.convertMonthYearToDate(month, year) );

		modelMap.addAttribute("monthOfReport", month);
		modelMap.addAttribute("yearOfReport", year);

		return "lossReportConsolidated";
	}

	
	//added
	@PreAuthorize("hasRole('ROLE_SLDC_USER') or hasRole('ROLE_SLDC_ADMIN')")
	@RequestMapping(value = "/getIRDetails", method = RequestMethod.GET)
	public String getIRDetails(@RequestParam(value = "month") Integer month,
			@RequestParam(value = "year") Integer year,ModelMap modelMap) {
		
		modelMap.addAttribute("iRDetails", lossReportService.getIRDetails(null, month, year));
		modelMap.addAttribute("reportMonthYearDate",DateUtil.convertMonthYearToDate(month, year) );

		modelMap.addAttribute("monthOfReport", month);
		modelMap.addAttribute("yearOfReport", year);

		return "iRDetails";
	}


}
