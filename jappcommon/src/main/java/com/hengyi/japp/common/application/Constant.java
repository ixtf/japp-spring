package com.hengyi.japp.common.application;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.hengyi.japp.common.data.BindCorporationType;
import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.domain.node.bind.corporation.HrCorporation;
import com.hengyi.japp.common.domain.node.bind.corporation.Oa1Corporation;
import com.hengyi.japp.common.domain.node.bind.corporation.Oa2Corporation;
import com.hengyi.japp.common.domain.node.bind.corporation.SapCorporation;
import com.hengyi.japp.common.domain.node.bind.user.InnerUser;
import com.hengyi.japp.common.domain.node.bind.user.SapUser;
import com.hengyi.japp.common.domain.node.bind.user.SsoUser;

@SuppressWarnings("rawtypes")
public final class Constant extends com.hengyi.japp.common.Constant {
	private static Map<PrincipalType, Class> principalTypeReferClass;
	private static Map<BindCorporationType, Class> bindCorporationTypeReferClass;

	public static final Map<PrincipalType, Class> getPrincipalTypeReferClass() {
		if (principalTypeReferClass == null) {
			ImmutableMap.Builder<PrincipalType, Class> builder = ImmutableMap
					.builder();
			builder.put(PrincipalType.EMAIL, InnerUser.class);
			builder.put(PrincipalType.MOBILE, InnerUser.class);

			builder.put(PrincipalType.SAP, SapUser.class);
			builder.put(PrincipalType.HR, SsoUser.class);
			builder.put(PrincipalType.OA1, SsoUser.class);
			builder.put(PrincipalType.OA2, SsoUser.class);
			builder.put(PrincipalType.SSO, SsoUser.class);
			principalTypeReferClass = builder.build();
		}
		return principalTypeReferClass;
	}

	public static final Map<BindCorporationType, Class> getBindCorporationTypeReferClass() {
		if (bindCorporationTypeReferClass == null) {
			ImmutableMap.Builder<BindCorporationType, Class> builder = ImmutableMap
					.builder();
			builder.put(BindCorporationType.SAP, SapCorporation.class);
			builder.put(BindCorporationType.HR, HrCorporation.class);
			builder.put(BindCorporationType.OA1, Oa1Corporation.class);
			builder.put(BindCorporationType.OA2, Oa2Corporation.class);
			bindCorporationTypeReferClass = builder.build();
		}
		return bindCorporationTypeReferClass;
	}

	public static class Relationship {
		public static final String BIND_USER = "login";
		public static final String BIND_CORPORATION = "bind";
		public static final String USER_CORPORATION = "staff";
		public static final String HR_CORPORATION_PARENT = "parent";
	}

	public static class AdminWeb {
		public static final String urlPrefix = "/admin";

		public static final String userList = urlPrefix + "/users";
		public static final String userListView = "admin/user/list";
		public static final String userSearch = urlPrefix + "/userSearch";

		public static final String bindUserList = urlPrefix + "/bindUsers";
		public static final String bindUserListView = "admin/bindUser/list";
		public static final String bindUserSearch = urlPrefix
				+ "/bindUserSearch";

		public static final String userUpdate = urlPrefix + "/user";
		public static final String userUpdateView = "admin/user/update";

		public static final String bindUserUpdate = urlPrefix + "/bindUser";
		public static final String bindUserUpdateView = "admin/bindUser/update";

		public static final String userBind = urlPrefix + "/userBind";
		public static final String userBindView = "admin/user/bind";

		public static final String bindUserBind = urlPrefix + "/bindUserBind";
		public static final String bindUserBindView = "admin/bindUser/bind";

		public static final String corporationList = urlPrefix
				+ "/corporations";
		public static final String corporationListView = "admin/corporation/list";

		public static final String bindCorporationList = urlPrefix
				+ "/bindCorporations";
		public static final String bindCorporationListView = "admin/bindCorporation/list";

		public static final String corporationUpdate = urlPrefix
				+ "/corporation";
		public static final String corporationUpdateView = "admin/corporation/update";

		public static final String bindCorporationUpdate = urlPrefix
				+ "/bindCorporation";
		public static final String bindCorporationUpdateView = "admin/bindCorporation/update";

		public static final String corporationBind = urlPrefix
				+ "/corporationBind";
		public static final String corporationBindView = "admin/corporation/bind";

		public static final String bindCorporationBind = urlPrefix
				+ "/bindCorporationBind";
		public static final String bindCorporationBindView = "admin/bindCorporation/bind";
	}
}
