package br.com.fsa.tccrastreamento.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.fsa.tccrastreamento.app.model.Token;
import br.com.fsa.tccrastreamento.app.repository.TokenRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Order(2)
public class FiltroReqRes implements Filter {
	
	@Autowired
	private TokenRepository tokenRep;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		System.out.println("MÈTODO: " + req.getMethod());
		System.out.println("URL: " + req.getRequestURI());
		
		if(tokenAutenticacaoValido(req))
		{
			chain.doFilter(request, response);	
		}
		else 
		{
			resp.setStatus(401);
		}
	}
	
	@Transactional
	public boolean tokenAutenticacaoValido(HttpServletRequest req) 
	{
		String token = req.getHeader("token-aut");
		
		System.out.println("TOKEN DE AUTENTICAÇÃO: ");
		System.out.println(token);
		
		
		if(token != null)
		{
			Optional<Token> optTk = tokenRep.findByHash(token);
			
			if(optTk.isPresent())
			{
				System.out.println("TOKEN EXISTE NO BD");
				
				Token tk = optTk.get();
				
				if(tk.getDataExpiracao().isBefore(LocalDateTime.now()))
				{
					System.out.println("TOKEN EXPIROU");
					
					return false;
				}
				
				tk.setDataExpiracao(LocalDateTime.now().plusHours(1));
				
				tokenRep.save(tk);
				
				return true;
			}
		
			System.out.println("TOKEN NAO EXISTE NO BD");
			return false;
		
		}
		
		return false;
	}
}
