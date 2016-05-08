package org.fxi.auth.service.impl;

import java.util.Date;

import org.fxi.auth.entity.PersistentLogin;
import org.fxi.auth.repo.PersistentLoginRepository;
import org.fxi.auth.service.PersistentLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.stereotype.Service;


@Service
public class PersistentLoginServiceImpl implements PersistentLoginService {
	
	@Autowired
	private PersistentLoginRepository repository;

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		PersistentLogin pl = new PersistentLogin();
		pl.setUsername(token.getUsername());
		pl.setSeries(token.getSeries());
		pl.setToken(token.getTokenValue());
		pl.setLastUsed(token.getDate());
		repository.saveAndFlush(pl);
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		PersistentLogin pl = repository.findOne(series);
		pl.setToken(tokenValue);
		pl.setLastUsed(lastUsed);
		repository.saveAndFlush(pl);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		PersistentLogin pl = repository.findOne(seriesId);
		return new PersistentRememberMeToken(pl.getUsername(), pl.getSeries(), pl.getToken(), pl.getLastUsed());
	}

	@Override
	public void removeUserTokens(String username) {
		PersistentLogin dpl = null;
		for (PersistentLogin pl : repository.findAll()) {
			if (pl.getUsername().equals(username)) {
				dpl = pl;
				break;
			}
		}
		if (dpl != null) {
			repository.delete(dpl);
			repository.flush();
		}
	}

}
