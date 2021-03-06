package de.techdev.trackr.domain;

import de.techdev.trackr.core.pdf.PdfRenderer;
import de.techdev.trackr.domain.common.UuidMapper;
import de.techdev.trackr.domain.employee.expenses.reports.ReportNotifyService;
import de.techdev.trackr.domain.employee.expenses.reports.ReportService;
import de.techdev.trackr.domain.employee.login.DeactivateEmployeesService;
import de.techdev.trackr.domain.employee.login.support.SupervisorService;
import de.techdev.trackr.domain.employee.sickdays.SickDaysNotifyService;
import de.techdev.trackr.domain.employee.vacation.HolidayCalculator;
import de.techdev.trackr.domain.employee.vacation.MailApproveService;
import de.techdev.trackr.domain.employee.vacation.VacationRequestApproveService;
import de.techdev.trackr.domain.employee.vacation.support.VacationRequestEmployeeToDaysTotalService;
import de.techdev.trackr.domain.employee.vacation.support.VacationRequestNotifyService;
import de.techdev.trackr.domain.employee.worktimetracking.WorkTimeTrackingReminderService;
import de.techdev.trackr.domain.project.invoice.ChangeStateService;
import de.techdev.trackr.domain.project.invoice.InvoiceOverdueService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * All Beans needed for the API that have nothing to do with web.
 *
 * @author Moritz Schulze
 */
@Configuration
@EnableTransactionManagement
@PropertySources({
        @PropertySource({"classpath:application_${spring.profiles.active:dev}.properties"}),
        @PropertySource(value = "${trackr.externalconfig:file:/etc/trackr.properties}", ignoreResourceNotFound = true)
})
public class ApiBeansConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ReportService travelExpenseReportService() {
        return new ReportService();
    }

    @Bean
    public ReportNotifyService travelExpenseReportNotifyService() {
        return new ReportNotifyService();
    }

    @Bean
    public WorkTimeTrackingReminderService workTimeTrackingReminderService() {
        return new WorkTimeTrackingReminderService();
    }

    @Bean
    public DeactivateEmployeesService deactivateEmployeesService() {
        return new DeactivateEmployeesService();
    }

    @Bean
    public VacationRequestNotifyService vacationRequestNotifyService() {
        return new VacationRequestNotifyService();
    }

    @Bean
    public VacationRequestApproveService vacationRequestService() {
        return new VacationRequestApproveService();
    }

    @Bean
    public VacationRequestEmployeeToDaysTotalService vacationRequestEmployeeToDaysTotalService() {
        return new VacationRequestEmployeeToDaysTotalService();
    }

    @Bean
    public HolidayCalculator holidayCalculator() {
        return new HolidayCalculator();
    }

    @Bean
    public InvoiceOverdueService invoiceOverduer() {
        return new InvoiceOverdueService();
    }

    @Bean
    public ChangeStateService changeStateService() {
        return new ChangeStateService();
    }

    @Bean
    public SickDaysNotifyService sickDaysNotifyService() {
        return new SickDaysNotifyService();
    }

    @Bean
    public SupervisorService supervisorService() {
        return new SupervisorService();
    }

    @Bean
    public PdfRenderer pdfRenderer() {
        return new PdfRenderer();
    }

    @Bean
    public MailApproveService mailApproveService() {
        return new MailApproveService();
    }

    @Bean
    public UuidMapper uuidMapper() {
        return new UuidMapper();
    }
}
