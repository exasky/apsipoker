import {Pipe, PipeTransform} from '@angular/core';
import {Tournament} from '../../championship/model/championship';

@Pipe({name: 'filterByDate'})
export class FilterByDatePipe implements PipeTransform {
  transform(value: Tournament[], before?: boolean, date?: Date): Tournament[] {
    const now = date ? date : new Date();
    now.setHours(0, 0, 0, 0);
    console.log(now);
    return value.filter(t => {
      const tDate = new Date(t.date);
      tDate.setHours(0, 0, 0, 0);
      console.log(tDate);
      return before === true ? tDate < now
        : before === false ? tDate > now
          : tDate.getTime() === now.getTime();
    });

  }
}
