package com.petro.span.client.gin;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.petro.span.client.AdminGatekeeper;
import com.petro.span.client.AuthUtil;
import com.petro.span.client.BPSSUtils;
import com.petro.span.client.ClientValidation;
import com.petro.span.client.DataService;
import com.petro.span.client.Loader;
import com.petro.span.client.LoggedInGatekeeper;
import com.petro.span.client.SessionFactory;
import com.petro.span.client.application.ApplicationModule;
import com.petro.span.client.place.NameTokens;
import com.petro.span.client.resources.Resources;
import com.petro.span.client.resources.ResourcesLoader;
import com.petro.span.shared.CurrentUser;
import com.petro.span.shared.UserInfo;

/**
 * See more on setting up the PlaceManager on <a
 * href="// See more on: https://github.com/ArcBees/GWTP/wiki/PlaceManager">DefaultModule's > DefaultPlaceManager</a>
 */
public class ClientModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new DefaultModule());
		install(new ApplicationModule());

		 install(new ServiceModule());


		// DefaultPlaceManager Places
		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.login);
		bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.login);
		bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.unauthorized);


	

		

		bind(Resources.class).in(Singleton.class);
		bind(ResourcesLoader.class).asEagerSingleton();

		bind(Loader.class).in(Singleton.class);
//		bind(UserInfo.class).asEagerSingleton();
		bind(UserInfo.class).in(Singleton.class);
		bind(AuthUtil.class).in(Singleton.class);
		bind(BPSSUtils.class).asEagerSingleton();
//		bind(PlayAreaList.class).in(Singleton.class);
		bind(CurrentUser.class).in(Singleton.class);
//		bind(CurrentUser.class).asEagerSingleton();;
		bind(LoggedInGatekeeper.class).in(Singleton.class);
		bind(AdminGatekeeper.class).in(Singleton.class);
		bind(SessionFactory.class).in(Singleton.class);
		bind(ClientValidation.class).in(Singleton.class);
		bind(DataService.class).in(Singleton.class);
		

	}
}