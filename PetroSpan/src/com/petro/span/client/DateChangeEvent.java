package com.petro.span.client;

import java.util.Date;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.datepicker.client.CalendarUtil;

/**
 * Creates a new value every time a date is accessed.
 */
class DateChangeEvent extends ValueChangeEvent<Date> {

  /**
   * Fires value change event if the old value is not equal to the new value.
   * Use this call rather than making the decision to short circuit yourself for
   * safe handling of null.
   * 
   * @param <S> The event source
   * @param source the source of the handlers
   * @param oldValue the oldValue, may be null
   * @param newValue the newValue, may be null
   */
  public static <S extends HasValueChangeHandlers<Date> & HasHandlers> void fireIfNotEqualDates(
      S source, Date oldValue, Date newValue) {
    if (ValueChangeEvent.shouldFire(source, oldValue, newValue)) {
      source.fireEvent(new DateChangeEvent(newValue));
    }
  }

  /**
   * Creates a new date value change event.
   * 
   * @param value the value
   */
  protected DateChangeEvent(Date value) {
    // The date must be copied in case one handler causes it to change.
    super(CalendarUtil.copyDate(value));
  }

  @Override
  public Date getValue() {
    return CalendarUtil.copyDate(super.getValue());
  }
}