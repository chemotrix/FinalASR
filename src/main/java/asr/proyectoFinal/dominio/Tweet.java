/*
 * Copyright IBM Corp. 2017
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package asr.proyectoFinal.dominio;

/**
 * Represents a Visitor document stored in Cloudant.
 */

public class Tweet {
	private String _id;
	private String _rev;
	private String idTweet = null;
	private String tweet = null;
	private String pic = null;
	private String tone = null;

	public Tweet() {
		this.idTweet = "";
		this.tweet = "";
		this.pic = "";
		this.tone = "";
	}
	
	public Tweet(String idTweet,String tweet,String pic,String tone){
		this.idTweet = idTweet;
		this.tweet = tweet;
		this.pic = pic;
		this.tone = tone;
	}
	/**
	 * Gets the ID.
	 * 
	 * @return The ID.
	 */
	public String get_id() {
		return _id;
	}

	public String getidTweet() {
		return idTweet;
	}

	public void setidTweet(String idTweet) {
		this.idTweet = idTweet;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getTone() {
		return tone;
	}

	public void setTone(String tone) {
		this.tone = tone;
	}

	/**
	 * Sets the ID
	 * 
	 * @param _id
	 *            The ID to set.
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * Gets the revision of the document.
	 * 
	 * @return The revision of the document.
	 */
	public String get_rev() {
		return _rev;
	}

	/**
	 * Sets the revision.
	 * 
	 * @param _rev
	 *            The revision to set.
	 */
	public void set_rev(String _rev) {
		this._rev = _rev;
	}
	
	
	
	@Override
	public String toString() {
		return "Tweet [idTweet=" + idTweet + ", tweet=" + tweet + ", pic=" + pic
				+ ", tone=" + tone + "]";
	}

}