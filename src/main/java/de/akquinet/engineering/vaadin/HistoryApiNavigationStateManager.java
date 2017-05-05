package de.akquinet.engineering.vaadin;

import com.vaadin.navigator.NavigationStateManager;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.Registration;

import java.net.URI;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class HistoryApiNavigationStateManager implements NavigationStateManager
{
    private final Page page;
    private Navigator navigator;
    private Registration popStateListenerRegistration;

    public HistoryApiNavigationStateManager(final Page page)
    {
        this.page = page;
    }

    @Override
    public String getState()
    {
        System.out.println(page.getLocation());
        System.out.println(page.getLocation().getFragment());
        System.out.println(page.getLocation().getHost());
        System.out.println(page.getLocation().getPath());
        System.out.println(page.getLocation().getPort());
        System.out.println(page.getLocation().getAuthority());
        System.out.println(page.getLocation().getQuery());
        System.out.println(VaadinServlet.getCurrent().getServletContext().getContextPath());

        final URI location = page.getLocation();
        final String state = location.getPath();
        final String query = location.getQuery();
        final String queryString = "";//StringUtils.isNotBlank(query) ? "?" + query : "";
        if (state != null && state.startsWith("/"))
        {
            return state.substring(1);
//            final String path = state.substring(1);
//            final int slashIndex = path.indexOf('/');
//            return path.substring(0, slashIndex >= 0 ? slashIndex : path.length());
//            return state.substring(1) + queryString;
        }
        return "";
    }

    @Override
    public void setState(final String state)
    {
        page.pushState("/" + (state != null ? state : ""));
    }

    @Override
    public void setNavigator(final Navigator navigator) {
        if (this.navigator == null && navigator != null) {
            popStateListenerRegistration = page.addPopStateListener((Page.PopStateListener) event ->
            {
                final String uri = event.getUri();
                System.out.println(uri);
                navigator.navigateTo(getState());
            });
        } else if (this.navigator != null && navigator == null) {
           popStateListenerRegistration.remove();
        }
        this.navigator = navigator;
    }
}
