import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'filter'})
export class FilterPipe implements PipeTransform {
  transform(value: any[], filter: string, fieldPaths: string | string[]): any {
    console.log('FILTERING')
    const fieldPathsAsArray: string[] = typeof fieldPaths === 'string' ? [fieldPaths] : fieldPaths;

    return value.filter(val => {
      return fieldPathsAsArray.some(fieldPath => {
        const splitPath = fieldPath.split('.');
        return this.getValue(val, splitPath).indexOf(filter) !== -1;
      });
    });
  }

  private getValue(value: any, path: string[]): string {
    let returnValue = value;
    path.forEach(pathElement => returnValue = returnValue[pathElement]);
    return returnValue;
  }
}

