package mymoney.ws;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Filtro para forçar que todas as respostas sejam HTTP 200.
 */
public class FiltroExcecoesWebService implements Filter {

	public void destroy() {
	}
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		if (resp instanceof HttpServletResponse) {
			chain.doFilter(req, new FiltroExcecoesWrapper(
					(HttpServletResponse) resp));
		} else
			chain.doFilter(req, resp);
	}
	public void init(FilterConfig conf) throws ServletException {
	}
	class FiltroExcecoesWrapper extends HttpServletResponseWrapper {
		public FiltroExcecoesWrapper(HttpServletResponse response) {
			super(response);
		}
		public void setStatus(int statusCode) {
			if (statusCode == 500) {
				super.setStatus(200);
			}
		}
	}

}
