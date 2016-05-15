package exercises.blue.demoagain.MainActvity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.ArrayList;

import exercises.blue.demoagain.interfaces.myOnItemClickListener;
import exercises.blue.demoagain.interfaces.myOnItemLongClickListener;
import exercises.blue.demoagain.userdata.friendsDatum;
import exercises.blue.demoagain.agendaFragement.agenda;
import exercises.blue.demoagain.friendsFragment.fragmentFriends;

/**
 * ViewPager's Adapter
 * Created by getbl on 2016/4/18.
 */
class fragmentPagerAdapter extends FragmentPagerAdapter  {

    fragmentFriends mFragmentFriendsAndroid;
    fragmentFriends mFragmentFriendsiOS;
    fragmentFriends mFragmentFriendsFront;
    FragmentManager fm;

    public fragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
        mFragmentFriendsAndroid = fragmentFriends.newInstance("Android");
        mFragmentFriendsiOS = fragmentFriends.newInstance("iOS");
        mFragmentFriendsFront = fragmentFriends.newInstance("前端");

        //friends = fragmentFriends.newInstance();
       // mAgenda = agenda.newInstance();

}

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return mFragmentFriendsAndroid;
        else if (position == 1) return mFragmentFriendsiOS;
        else if (position == 2) return mFragmentFriendsFront;
        else return null;
    }

    /**
     * 网上找来的方法1
     * http://blog.csdn.net/z13759561330/article/details/40737381
     * 然而并没有什么用
     * @param container container
     * @param position  position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }


//    public void addFriends(friendsDatum datum, int position) {
//        friends.getAdapter().add(datum, position);
//        setFragments(friends);
//    }
//
//    public void addFriends(friendsDatum datum) {
//        friends.getAdapter().add(datum);
//        setFragments(friends);
//    }
//
//    public void removeFriends(int position) {
//        friends.getAdapter().remove(position);
//        setFragments(friends);
//    }
//
//    public void addAgenda(int position) {
//        mAgenda.getAdapter().addItem(position);
//        setFragments(mAgenda);
//    }
//
//    public void removeAgenda(int position) {
//        mAgenda.getAdapter().removeItem(position);
//        setFragments(mAgenda);
//    }


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
//        friends.getd().addList(data);
//        setFragments(friends);
//    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Android";
            case 1:
                return "iOS";
            case 2:
                return "前端";
            default:
                return "错误!";
        }
    }



//    private myOnItemClickListener mClickListener;
//    private myOnItemLongClickListener mLongClickListener;
//    public void setListeners(myOnItemClickListener clickListener, myOnItemLongClickListener longClickListener){
//        mClickListener=clickListener;
//        mLongClickListener=longClickListener;
//        setListenersToFragments();
//    }
//    private void setListenersToFragments(){
//        if(mFragmentFriendsAndroid!=null){
//            mFragmentFriendsAndroid.setClickListeners(mClickListener,mLongClickListener);
//        }
//        if(mFragmentFriendsiOS!=null){
//            mFragmentFriendsiOS.setClickListeners(mClickListener,mLongClickListener);
//        }
//        if(mFragmentFriendsFront!=null){
//            mFragmentFriendsFront.setClickListeners(mClickListener,mLongClickListener);
//        }
//    }
}
