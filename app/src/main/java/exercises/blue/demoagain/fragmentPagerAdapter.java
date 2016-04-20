package exercises.blue.demoagain;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import layout.agenda;
import layout.fragmentFriends;

/**
 * Created by getbl on 2016/4/18.
 */
class fragmentPagerAdapter extends FragmentPagerAdapter {
    fragmentFriends friends;
    public fragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        friends = fragmentFriends.newInstance();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return fragmentFriends.newInstance();
        else if (position == 1) return agenda.newInstance("welcome", "hello world");
        else if (position == 2) return fragmentFriends.newInstance();
        else return null;
    }

    @Override
    public int getCount() {
        return 3;
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
