package com.github.tvbox.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AbsJson implements Serializable {

    public int code;
    public String limit;
    public ArrayList<AbsJsonVod> list;
    public String msg;
    public int page;
    public int pagecount;
    public int total;


    public class AbsJsonVod implements Serializable {
        public int group_id;
        public int type_id;
        public int type_id_1;
        public String type_name;
        public String vod_actor;
        public String vod_area;
        public String vod_author;
        public String vod_behind;
        public String vod_blurb;
        public String vod_class;
        public String vod_color;
        public String vod_content;
        public String vod_copyright;
        public String vod_director;
        public String vod_douban_id;
        public String vod_douban_score;
        public String vod_down;
        public String vod_down_from;
        public String vod_down_note;
        public String vod_down_server;
        public String vod_down_url;
        public String vod_duration;
        public String vod_en;
        public String vod_hits;
        public String vod_hits_day;
        public String vod_hits_month;
        public String vod_hits_week;
        public String vod_id;
        public String vod_isend;
        public String vod_jumpurl;
        public String vod_lang;
        public String vod_letter;
        public String vod_level;
        public String vod_lock;
        public String vod_name;
        public String vod_pic;
        public String vod_pic_screenshot;
        public String vod_pic_slide;
        public String vod_pic_thumb;
        public String vod_play_from;
        public String vod_play_note;
        public String vod_play_server;
        public String vod_play_url;
        public String vod_plot;
        public String vod_plot_detail;
        public String vod_plot_name;
        public String vod_points;
        public String vod_points_down;
        public String vod_points_play;
        public String vod_pubdate;
        public String vod_pwd;
        public String vod_pwd_down;
        public String vod_pwd_down_url;
        public String vod_pwd_play;
        public String vod_pwd_play_url;
        public String vod_pwd_url;
        public String vod_rel_art;
        public String vod_rel_vod;
        public String vod_remarks;
        public String vod_reurl;
        public String vod_score;
        public String vod_score_all;
        public String vod_score_num;
        public String vod_serial;
        public String vod_state;
        public String vod_status;
        public String vod_sub;
        public String vod_tag;
        public String vod_time;
        public String vod_time_add;
        public String vod_time_hits;
        public String vod_time_make;
        public String vod_total;
        public String vod_tpl;
        public String vod_tpl_down;
        public String vod_tpl_play;
        public String vod_trysee;
        public String vod_tv;
        public String vod_up;
        public String vod_version;
        public String vod_weekday;
        public String vod_writer;
        public String vod_year;

        public Movie.Video toXmlVideo() {
            Movie.Video video = new Movie.Video();
            video.last = vod_time;
            video.id = vod_id;
            video.tid = type_id;
            video.name = vod_name;
            video.type = type_name;
            video.pic = vod_pic;
            video.lang = vod_lang;
            video.area = vod_area;
            try {
                video.year = Integer.parseInt(vod_year);
            } catch (Throwable th) {
                video.year = 0;
            }
            video.state = vod_state;
            video.note = vod_remarks;
            video.actor = vod_actor;
            video.director = vod_director;
            Movie.Video.UrlBean urlBean = new Movie.Video.UrlBean();
            if (vod_play_from != null && vod_play_url != null) {
                String[] playFlags = vod_play_from.split("\\$\\$\\$");
                String[] playUrls = vod_play_url.split("\\$\\$\\$");
                List<Movie.Video.UrlBean.UrlInfo> infoList = new ArrayList<>();
                for (int i = 0; i < playFlags.length; i++) {
                    Movie.Video.UrlBean.UrlInfo urlInfo = new Movie.Video.UrlBean.UrlInfo();
                    urlInfo.flag = playFlags[i];
                    if (i < playUrls.length)
                        urlInfo.urls = playUrls[i];
                    else
                        urlInfo.urls = "";
                    infoList.add(urlInfo);
                }
                urlBean.infoList = infoList;
            }
            video.urlBean = urlBean;
            video.des = vod_content;
            return video;
        }
    }

    public AbsXml toAbsXml() {
        AbsXml xml = new AbsXml();
        Movie movie = new Movie();
        movie.page = page;
        movie.pagecount = pagecount;
        try {
            movie.pagesize = Integer.parseInt(limit);
        } catch (Throwable th) {
            movie.pagesize = 0;
        }
        movie.recordcount = total;
        List<Movie.Video> videoList = new ArrayList<>();
        for (AbsJsonVod vod : list) {
            try {
                videoList.add(vod.toXmlVideo());
            } catch (Throwable th) {
                movie.pagesize = 0;
            }
        }
        movie.videoList = videoList;
        xml.movie = movie;
        return xml;
    }
}
