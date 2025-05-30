# hclHealthCareHackathon
API endpoints:

/login
{
username,
password
}
Response
{
status: true
}

/createShift
{
date,
type,
capacity
}
Response
{
id
statusCode
}

/getShiftByDate
{
  date
}
Response
{
List<ShiftInstance>
}


/addStaff
Request
{
  name,
  role,
  shift_preference,
  email,
  contact
}
Response {
  status,
  id
}

/removeStaff/:id
Response 
{
  is_deleted: true/false
}

/fetchAllStaffs
Response
{
List<Staffs>
}
