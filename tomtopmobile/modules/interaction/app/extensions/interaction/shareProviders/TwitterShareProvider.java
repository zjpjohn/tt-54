package extensions.interaction.shareProviders;

import javax.inject.Singleton;

import play.twirl.api.Html;
import extensions.base.IShareProvider;

@Singleton
public class TwitterShareProvider implements IShareProvider {

	@Override
	public boolean isPureJS() {
		return true;
	}

	@Override
	public Html getShareButton(String url, String image, String title) {
		return views.html.shares.twitter.render(url);
	}

	@Override
	public int getDisplayOrder() {
		return 2;
	}

}
