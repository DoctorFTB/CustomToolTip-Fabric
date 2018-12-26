package ftblag.customtooltip.mixin;

import ftblag.customtooltip.gson.CTTGsonParser;
import net.minecraft.client.item.TooltipOptions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public class MixinItemStack {

    @Inject(method = "getTooltipText(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/client/item/TooltipOptions;)Ljava/util/List;", at = @At("RETURN"))
    public void getTooltipText(PlayerEntity var1, TooltipOptions var2, CallbackInfoReturnable info) {
        Item stack = ((ItemStack) (Object) this).getItem();
        if (CTTGsonParser.tooltips.containsKey(stack)) {
            List<TextComponent> list = (List<TextComponent>) info.getReturnValue();
            for (String str : CTTGsonParser.tooltips.get(stack))
                list.add(new StringTextComponent(str));
        }
    }
}
