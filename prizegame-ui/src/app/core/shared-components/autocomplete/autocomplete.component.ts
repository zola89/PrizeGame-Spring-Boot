import {Component, ElementRef, Input, ViewChild} from '@angular/core';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {MatChipInputEvent} from '@angular/material';
import {MatAutocompleteSelectedEvent} from '../../../../../node_modules/@angular/material/typings/esm5/autocomplete';
import {map, startWith} from 'rxjs/operators';

@Component({
  selector: 'app-autocomplete',
  templateUrl: './autocomplete.component.html',
  styleUrls: ['./autocomplete.component.css']
})
export class AutocompleteComponent {

  selectable = true;
  removable = true;
  addOnBlur = false;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  itemCtrl = new FormControl();
  filteredItems: Observable<any[]>;

  @Input()
  items: any[] = [];

  @Input()
  allItems: any[] = [];

  @Input()
  property: string = null;

  @Input()
  public placeHolderLabel: string = 'Choose';

  @ViewChild('itemInput') itemInput: ElementRef;

  constructor() {

    this.filteredItems = this.itemCtrl.valueChanges.pipe(
      startWith(null),
      map((value: string | null) => value ? this._filter(value) : this.allItems.slice()));

  }

  add(event: MatChipInputEvent): void {
    const input = event.input;

    // Reset the input value
    if (input) {
      input.value = '';
    }

    this.itemCtrl.setValue(null);
  }

  remove(item: any): void {

    const index = this.items.indexOf(item);

    if (index >= 0) {
      this.items.splice(index, 1);
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {

    if (this.getItemByProperty(this.items, event.option.viewValue)) {
      return;
    }

    this.items.push(this.getItemByProperty(this.allItems, event.option.viewValue));
    this.itemInput.nativeElement.value = '';
    this.itemCtrl.setValue(null);
  }

  private _filter(value: string): any[] {

    let _self = this;

    const filterValue = value.length != undefined ? value.toLowerCase() : '';

    return this.allItems.filter(item => this.getItemByProperty(_self.items, item[_self.property]) == null && item[_self.property].toLowerCase().indexOf(filterValue) >= 0);
  }

  private getItemByProperty(collection: any, propertyValue) {

    for (let i = 0; i < collection.length; i++) {
      if (collection[i][this.property].trim() == propertyValue) {
        return collection[i];
      }
    }

    return null;
  }

}
