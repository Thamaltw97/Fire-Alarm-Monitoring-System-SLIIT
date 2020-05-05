using FireAlarmMonitoringSystem.Web.Data.Entities;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace FireAlarmMonitoringSystem.Web.Data.Persistence
{
    public class UserPersistenceService : IUserPersistenceService
    {
        private readonly AppDbContext _context;

        public UserPersistenceService() { }

        public UserPersistenceService(AppDbContext context)
        {
            _context = context;
        }

        public async Task<UserModel> SignIn(UserModel user)
        {
            UserModel actualUser = await _context.Users.FirstOrDefaultAsync(
                dbUserObj => dbUserObj.userEmail.Equals(user.userEmail) && dbUserObj.userPassword.Equals(user.userPassword)
            );

            if (actualUser == null)
            {
                return null;
            }
            else
            {
                //actualUser.userModel = await _context.Users.FirstOrDefaultAsync(userModel => userModel.userId == actualUser.userId);
                return actualUser;
            }
            
        }

        public async Task<ApiResult> AddUser(UserModel user)
        {
            if (user != null)
            {
                await _context.AddAsync(user);
                try
                {
                    await _context.SaveChangesAsync();
                    return new ApiResult { BOOVAL = true, MSG = "Sucessfully Saved." };
                }
                catch (DbUpdateException dbUpdateEx)
                {
                    return new ApiResult { BOOVAL = false, MSG = "Error saving data - " + dbUpdateEx };
                }
                catch (Exception ex)
                {
                    return new ApiResult { BOOVAL = false, MSG = "Error saving data - " + ex };
                }
            }
            else
            {
                return new ApiResult { BOOVAL = false, MSG = "Please enter correct sensor details !" };
            }
        }
    }
}
