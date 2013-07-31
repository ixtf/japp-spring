package com.hengyi.japp.common.data;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class Theme implements Serializable {
	private static final long serialVersionUID = -7274327414197756065L;
	private String name;
	private String image;

	public static final List<Theme> getDefault() {
		ImmutableList.Builder<Theme> result = ImmutableList.builder();
		result.add(new Theme("afterdark", "afterdark.png"));
		result.add(new Theme("afternoon", "afternoon.png"));
		result.add(new Theme("afterwork", "afterwork.png"));
		result.add(new Theme("aristo", "aristo.png"));
		result.add(new Theme("black-tie", "black-tie.png"));
		result.add(new Theme("blitzer", "blitzer.png"));
		result.add(new Theme("bluesky", "bluesky.png"));
		result.add(new Theme("bootstrap", "bootstrap.png"));
		result.add(new Theme("casablanca", "casablanca.png"));
		result.add(new Theme("cruze", "cruze.png"));
		result.add(new Theme("cupertino", "cupertino.png"));
		result.add(new Theme("dark-hive", "dark-hive.png"));
		result.add(new Theme("dot-luv", "dot-luv.png"));
		result.add(new Theme("eggplant", "eggplant.png"));
		result.add(new Theme("excite-bike", "excite-bike.png"));
		result.add(new Theme("flick", "flick.png"));
		result.add(new Theme("glass-x", "glass-x.png"));
		result.add(new Theme("home", "home.png"));
		result.add(new Theme("hot-sneaks", "hot-sneaks.png"));
		result.add(new Theme("humanity", "humanity.png"));
		result.add(new Theme("le-frog", "le-frog.png"));
		result.add(new Theme("midnight", "midnight.png"));
		result.add(new Theme("mint-choc", "mint-choc.png"));
		result.add(new Theme("overcast", "overcast.png"));
		result.add(new Theme("pepper-grinder", "pepper-grinder.png"));
		result.add(new Theme("redmond", "redmond.png"));
		result.add(new Theme("rocket", "rocket.png"));
		result.add(new Theme("sam", "sam.png"));
		result.add(new Theme("smoothness", "smoothness.png"));
		result.add(new Theme("south-street", "south-street.png"));
		result.add(new Theme("start", "start.png"));
		result.add(new Theme("sunny", "sunny.png"));
		result.add(new Theme("swanky-purse", "swanky-purse.png"));
		result.add(new Theme("trontastic", "trontastic.png"));
		result.add(new Theme("ui-darkness", "ui-darkness.png"));
		result.add(new Theme("ui-lightness", "ui-lightness.png"));
		result.add(new Theme("vader", "vader.png"));
		return result.build();
	}

	public Theme() {
	}

	public Theme(String name, String image) {
		this.name = name;
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}