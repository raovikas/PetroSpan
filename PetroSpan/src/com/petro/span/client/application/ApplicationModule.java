package com.petro.span.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.petro.span.client.application.header.HeaderModule;
import com.petro.span.client.application.registration.RegistrationModule;
import com.petro.span.client.application.tabbar.TabBarModule;
import com.petro.span.client.application.login.AuthorizationPageModule;
import com.petro.span.client.application.operational.OperationalTabModule;
import com.petro.span.client.application.technical.TechnicalTabModule;
import com.petro.span.client.application.bydefault.DefaultTabModule;
import com.petro.span.client.application.commercial.CommercialTabModule;
import com.petro.span.client.application.current.selection.CurrentSelectionWidgetModule;
import com.petro.span.client.application.forecast.ForecastModule;
import com.petro.span.client.application.production.ProductionTabModule;
public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
		install(new UnauthorizedModule());
		install(new RegistrationModule());
		install(new AuthorizationPageModule());
		install(new CurrentSelectionWidgetModule());
		install(new ProductionTabModule());
		install(new ForecastModule());
		install(new CommercialTabModule());
		install(new TechnicalTabModule());
		install(new OperationalTabModule());
		install(new DefaultTabModule());
		install(new TabBarModule());
		install(new HeaderModule());
	

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}