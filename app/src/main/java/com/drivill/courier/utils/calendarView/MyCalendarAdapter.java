/*
package com.sachin.drivil.utils.calendarView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sachin.drivil.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyCalendarAdapter {
    Context _context;
    ArrayList<EventDateSelectionBean> listEvents;

    public MyCalendarAdapter(Context context, ArrayList<EventDateSelectionBean> listEvents, int month, int year) {
        super();
        this._context = context;
        dateDefaultTextColor = context.getResources().getColor(R.color.calendar_date_default_text_color);
        dateInEventTextColor = context.getResources().getColor(R.color.calendar_date_in_event_text_color);
        dateInEventSelectedTextColor = context.getResources().getColor(R.color.calendar_date_in_event_selected_text_color);

        this.list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
        setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
        this.listEvents = listEvents;

        currentMonth = month;
        currentYear = year;

        // Print Month
        printMonth(month, year);
        eventCalendar = Calendar.getInstance();
        eventCalendar.setTimeInMillis(System.currentTimeMillis());
        currentDayCalendar = Calendar.getInstance();
        currentDayCalendar.setTimeInMillis(System.currentTimeMillis());

        currentDayCalendar.set(Calendar.MONTH, currentMonth);
        currentDayCalendar.set(Calendar.YEAR, currentYear);
    }

    private String getMonthAsString(int i) {
        return months[i];
    }

    private int getNumberOfDaysOfMonth(int i) {
        if (currentYear % 4 == 0 && i == 1) {
            return daysOfMonth[i] + 1;
        } else {
            return daysOfMonth[i];
        }
    }

    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    private void printMonth(int mm, int yyyy) {
        int trailingSpaces = 0;
        int daysInPrevMonth = 0;
        int prevMonth = 0;
        int prevYear = 0;
        int nextMonth = 0;
        int nextYear = 0;

        int currentMonth = mm*/
/* - 1*//*
;
        daysInMonth = getNumberOfDaysOfMonth(currentMonth);

        // Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
        GregorianCalendar cal = new GregorianCalendar(yyyy, currentMonth, 1);

        if (currentMonth == 11) {
            prevMonth = currentMonth - 1;
            daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
            nextMonth = 0;
            prevYear = yyyy;
            nextYear = yyyy + 1;
        } else if (currentMonth == 0) {
            prevMonth = 11;
            prevYear = yyyy - 1;
            nextYear = yyyy;
            daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
            nextMonth = 1;
        } else {
            prevMonth = currentMonth - 1;
            nextMonth = currentMonth + 1;
            nextYear = yyyy;
            prevYear = yyyy;
            daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
        }

        int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        trailingSpaces = currentWeekDay;

        // Trailing Month days
        for (int i = 0; i < trailingSpaces; i++) {
            list.add(String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i) + "-GREY" + "-" + getMonthAsString(prevMonth) + "-" + prevYear);
        }

        // Current Month Days
        for (int i = 1; i <= daysInMonth; i++) {
            if (i == getCurrentDayOfMonth())
                list.add(String.valueOf(i) + "-BLUE" + "-" + getMonthAsString(currentMonth) + "-" + yyyy);
            else
                list.add(String.valueOf(i) + "-WHITE" + "-" + getMonthAsString(currentMonth) + "-" + yyyy);
        }

        // Leading Month days
        for (int i = 0; i < list.size() % 7; i++) {
            list.add(String.valueOf(i + 1) + "-GREY" + "-" + getMonthAsString(nextMonth) + "-" + nextYear);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Blue - Current Day
        //White - Current Month
        //Grey - Next Month
        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.calendar_day_gridcell, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // ACCOUNT FOR SPACING
        String[] day_color = list.get(position).split("-");
        String theday = day_color[0];

        // Set the Day GridCell
        holder.btnDate.setText(theday);

        if (day_color[1].equals("WHITE") || day_color[1].equals("BLUE")) {      //For Current Month
            holder.btnDate.setTextColor(dateDefaultTextColor);
            holder.btnDate.setBackgroundResource(R.drawable.backround_gray);

            currentDayCalendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(theday));

            for (int i = 0; i < listEvents.size(); i++) {

                EventDateSelectionBean eventDate = listEvents.get(i);
                setDataInEventCalendar(eventDate.getDate());

                if (Commons.checkDateEquality(currentDayCalendar, eventCalendar)) {

                    final EventDateSelectionBean finalEventDate = eventDate;
                    if (finalEventDate.isSelected()) {
                        holder.btnDate.setBackgroundResource(R.drawable.date_selected_bg);
                        holder.btnDate.setTextColor(dateInEventSelectedTextColor);

                    } else {
                        holder.btnDate.setBackgroundResource(R.drawable.date_unselected);
                        holder.btnDate.setTextColor(dateInEventTextColor);
                    }

                    holder.btnDate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (finalEventDate.isSelected()) {
                                finalEventDate.setSelected(false);
                                holder.btnDate.setBackgroundResource(R.drawable.date_unselected);
                                holder.btnDate.setTextColor(dateInEventTextColor);

                            } else {
                                finalEventDate.setSelected(true);
                                holder.btnDate.setBackgroundResource(R.drawable.date_selected_bg);
                                holder.btnDate.setTextColor(dateInEventSelectedTextColor);
                            }
                        }
                    });
                    break;
                }
            }
        } else {       //For Previous and next month dates
            holder.btnDate.setAlpha(0.4f);
            holder.btnDate.setBackgroundResource(R.drawable.backround_gray);
            //holder.btnDate.setVisibility(View.INVISIBLE);
            holder.llBtnParent.setVisibility(View.INVISIBLE);
        }

        if (position == list.size() - 1) {
            //Set Default data
        }
        return convertView;
    }
//End of getView()

    public class ViewHolder {
        Button btnDate;
        LinearLayout llBtnParent;

        public ViewHolder(View view) {
            btnDate = (Button) view.findViewById(R.id.calendar_day_gridcell);
            llBtnParent = (LinearLayout) view.findViewById(R.id.llBtnParent);
        }
    }

    public int getCurrentDayOfMonth() {
        return currentDayOfMonth;
    }

    private void setCurrentDayOfMonth(int currentDayOfMonth) {
        this.currentDayOfMonth = currentDayOfMonth;
    }

    public void setCurrentWeekDay(int currentWeekDay) {
        this.currentWeekDay = currentWeekDay;
    }

    private void setDataInEventCalendar(String date) {
        String splittedDate[] = date.split("-");
        eventCalendar.set(Calendar.YEAR, Integer.valueOf(splittedDate[0]));
        eventCalendar.set(Calendar.MONTH, (Integer.parseInt(splittedDate[1]) - 1));
        eventCalendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(splittedDate[2]));

    }
//End of setDataInEventCalendar()
}

*/
