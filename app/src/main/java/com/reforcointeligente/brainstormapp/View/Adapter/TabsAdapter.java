package com.reforcointeligente.brainstormapp.View.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.reforcointeligente.brainstormapp.View.Fragment.LessonFragment;
import com.reforcointeligente.brainstormapp.View.Fragment.StudentFragment;
import com.reforcointeligente.brainstormapp.View.Fragment.TeacherFragment;

public class TabsAdapter extends FragmentPagerAdapter{

    private static final int tabsQuantity = 3;

    public TabsAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return StudentFragment.newInstance();
            case 1:
                return LessonFragment.newInstance();
            case 2:
                return TeacherFragment.newInstance();
            default:
                /* Nothing to do */
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabsQuantity;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Aluno";
            case 1:
                return "Aula";
            case 2:
                return "Professor";
            default:
                /* Nothing to do */
                break;
        }
        return null;
    }
}
