import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RoomService} from '../room.service';
import {Room} from '../model/room';

@Component({
  selector: 'app-update-room',
  templateUrl: './update-room.component.html',
  styleUrls: ['./update-room.component.css']
})
export class UpdateRoomComponent implements OnInit {

   id: number;
   room: Room;
   submitted = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private roomService: RoomService
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.roomService.getRoom(this.id).subscribe(
      data => {
        console.log(data);
        this.room = data;
      },
      error => console.log(error)
    );
  }

  updateRoom(): void {
    this.roomService.updateRoom(this.id, this.room).subscribe(
      data => console.log(data),
      error => console.log(error)
    );
    this.room = new Room();
    this.goToList();
  }

  onSubmit(): void {
    this.updateRoom();
  }

  private goToList(): void {
    this.router.navigate(['/rooms']);
  }
}
