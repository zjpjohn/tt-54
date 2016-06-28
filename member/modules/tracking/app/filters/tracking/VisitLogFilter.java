package filters.tracking;

import play.libs.F.Promise;
import play.mvc.Http.Context;
import play.mvc.Result;
import services.base.HtmlUtils;
import extensions.filter.FilterExecutionChain;
import extensions.filter.IFilter;

public class VisitLogFilter implements IFilter {

	@Override
	public int priority() {
		return 99;
	}

	@Override
	public Promise<Result> call(Context context, FilterExecutionChain chain)
			throws Throwable {
		HtmlUtils.misc(context).addTailOnce(
				views.html.tracking.tracker.render());
		return chain.executeNext(context);
	}

}
