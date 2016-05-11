package exercises.blue.demoagain.MainActvity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.ArrayList;

import exercises.blue.demoagain.userdata.friendsDatum;
import exercises.blue.demoagain.agendaFragement.agenda;
import exercises.blue.demoagain.friendsFragment.fragmentFriends;

/**
 * ViewPager's Adapter
 * Created by getbl on 2016/4/18.
 */
class fragmentPagerAdapter extends FragmentPagerAdapter {
    static fragmentFriends friends;
    static agenda mAgenda;
    FragmentManager fm;

    public fragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
        friends = fragmentFriends.newInstance();
        mAgenda = agenda.newInstance();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return friends;
        else if (position == 1) return mAgenda;
        else if (position == 2) return friends;
        else return null;
    }

    /**
     * 网上找来的方法1
     * http://blog.csdn.net/z13759561330/article/details/40737381
     *
     * @param container container
     * @param position  position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

//        Fragment fragment = (Fragment) super.instantiateItem(container,position);
//        String tag = fragment.getTag();
//        //去除了判断是否要更新
//        //直接更新
//        if(fragment instanceof fragmentFriends){
//
//        FragmentTransaction transaction = fm.beginTransaction();
//        transaction.remove(fragment);
//        //网站上的方法中有个储存fragment的数组 这里直接换成了
//        //创建新的fragment
//        fragment=fragmentFriends.newInstance();
//
//        transaction.add(container.getId(),fragment,tag);
//        transaction.attach(fragment);
//        transaction.commit();
//        }
//        return fragment;
        return super.instantiateItem(container, position);
    }


    public void addFriends(friendsDatum datum, int position) {
        friends.getAdapter().add(datum, position);
        setFragments(friends);
    }

    public void addFriends(friendsDatum datum) {
        friends.getAdapter().add(datum);
        setFragments(friends);
    }

    public void removeFriends(int position) {
        friends.getAdapter().remove(position);
        setFragments(friends);
    }

    public void addAgenda(int position) {
        mAgenda.getAdapter().addItem(position);
        setFragments(mAgenda);
    }

    public void removeAgenda(int position) {
        mAgenda.getAdapter().removeItem(position);
        setFragments(mAgenda);
    }


    /**
     * 更新fragment
     * 原帖http://blog.sina.com.cn/s/blog_783ede03010173b4.html
     * 改进 -- 只要隐藏在再显示就可以了
     *
     * @param fragment 需要更新的fragment
     */
    public void setFragments(Fragment fragment) {
        FragmentTransaction ft = fm.beginTransaction();

        if (fragment instanceof fragmentFriends || fragment instanceof agenda) {
            ft.hide(fragment);
            ft.show(fragment);
        }

        /**
         * fragment在其他Activity返回时失去状态了
         * 用CommitAllowingStateLoss兼容
         */
        ft.commitAllowingStateLoss();
        notifyDataSetChanged();


    }

//    public void refreshFriends(){
//        ArrayList<friendsDatum> data = new ArrayList<>();
//        data.add(new friendsDatum("33","22"));
//        data.add(new friendsDatum("33","22"));
//        data.add(new friendsDatum("33","22"));
//        friends.getAdapter().addList(data);
//        setFragments(friends);
//    }

    public void clear(){
        friends.getAdapter().clear();
    }


    public Fragment getFragment(int position) {
        switch (position) {
            case 0:
                return friends;
            case 1:
                return mAgenda;
            case 2:
                return friends;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "动态";
            case 1:
                return "我的活动";
            case 2:
                return "活动";
            default:
                return "错误!";
        }
    }
}
