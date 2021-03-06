package extensions.loyalty.campaign.rule.signin;

import services.campaign.CampaignContext;
import services.campaign.IActionRule;
import services.campaign.IActionRuleParameter;
import codec.ICodec;

import com.fasterxml.jackson.databind.JsonNode;

public class SigninActionRule implements IActionRule {

	public static final String ID = "sign-in-rule";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public boolean match(CampaignContext context, IActionRuleParameter param) {
		return true;
	}

	@Override
	public ICodec<IActionRuleParameter, JsonNode> getParameterCodec() {
		return null;
	}

}
